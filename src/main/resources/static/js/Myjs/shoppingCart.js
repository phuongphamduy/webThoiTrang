const app = angular.module("shopping-cart", []);


app.controller("cartCtrl", function($scope, $http) {

    $http.get("/rest/users")
    .then(resp => {
        var user = resp.data.find(user => user.username == $('#user').text())
        $scope.name = user.fullname;
        $scope.address = user.address;
        $scope.phone = user.phone;
        $scope.note = '';
    })
    $scope.userId;
    $scope.qty = 1;
   

    $scope.cart = {
        items: [],

        saveToLocalStore() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        loadFromLocalStore() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

        add(id) {
            var item = this.items.find(item => item.id == id);
            if(item) {
                item.qty+=$scope.qty;
                this.saveToLocalStore();
            }else {
                $http.get(`/rest/products/${id}`)
                .then(resp => {
                    resp.data.qty = $scope.qty;
                    this.items.push(resp.data);
                    this.saveToLocalStore();
                })
                .catch(error => {
                    console.log(error);
                })
            }
            
        },

        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStore();
        },

        clear() {
            this.items = [];
            this.saveToLocalStore();
        },

        getSum() {
            var sum = 0;
            this.items.forEach(item => sum+=((item.price - (item.price * item.discount / 100)) * item.qty))
            return sum;
        },

        getNumberProduct() {
            var count = 0;
            this.items.forEach(item => count++);
            return count;
        }
    }

    $scope.order = {
        account: {username: $('#user').text()},
        createdate: new Date(),
        address: $scope.address,
        phone: $scope.phone,
        note: $scope.note,
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price * item.discount,
                    quantity: item.qty
                }
            })
        },
        purchase() {
            var phoneno = /((09|03|07|08|05)+([0-9]{8})\b)/g;
            var inputPhone = $('#phone').val();
            if(phoneno.test(inputPhone)) {
                var order = angular.copy(this);
                order.address = $scope.address;
                order.phone = $scope.phone;
                order.note = $scope.note;
                $http.post("/rest/orders", order)
                .then(resp => {
                    alert("Đặt hàng thành công");
                    $scope.cart.clear();
                    location.href= "/cart/success/" + resp.data.id;
                })
                .catch(error => {
                    alert("thanh toán thất bại")
                    console.log(error);
                })
            }else {
                alert("Số điện thoại chưa nhập đúng");
            }
            
        }
    }

    $scope.cart.loadFromLocalStore();
})