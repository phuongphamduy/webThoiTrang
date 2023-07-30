const app = angular.module("shopping-cart", []);

app.controller("cartCtrl", function($scope, $http) {
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
                item.qty++;
                this.saveToLocalStore();
            }else {
                $http.get(`/rest/products/${id}`)
                .then(resp => {
                    resp.data.qty = 1;
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
        }
    }

    $scope.cart.loadFromLocalStore();
})