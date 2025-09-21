class MovieRentingSystem {
private:
    unordered_map<int, set<pair<int, int>>> available; // movie -> {(price, shop)}
    unordered_map<long long, int> priceMap;
    set<tuple<int, int, int>> rented; // {(price, shop, movie)}

    long long f(int shop, int movie) {
        return ((long long) shop << 30) | movie;
    }

public:
    MovieRentingSystem(int n, vector<vector<int>>& entries) {
        for (auto& e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            available[movie].insert({price, shop});
            priceMap[f(shop, movie)] = price;
        }
    }

    vector<int> search(int movie) {
        vector<int> res;
        if (!available.count(movie)) {
            return res;
        }
        int cnt = 0;
        for (auto& [price, shop] : available[movie]) {
            res.push_back(shop);
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }

    void rent(int shop, int movie) {
        int price = priceMap[f(shop, movie)];
        available[movie].erase({price, shop});
        rented.insert({price, shop, movie});
    }

    void drop(int shop, int movie) {
        int price = priceMap[f(shop, movie)];
        rented.erase({price, shop, movie});
        available[movie].insert({price, shop});
    }

    vector<vector<int>> report() {
        vector<vector<int>> res;
        int cnt = 0;
        for (auto& [price, shop, movie] : rented) {
            res.push_back({shop, movie});
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }
};

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem* obj = new MovieRentingSystem(n, entries);
 * vector<int> param_1 = obj->search(movie);
 * obj->rent(shop,movie);
 * obj->drop(shop,movie);
 * vector<vector<int>> param_4 = obj->report();
 */
