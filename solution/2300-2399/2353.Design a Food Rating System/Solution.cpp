using pis = pair<int, string>;

class FoodRatings {
    map<string, pis> mp;
    map<string, set<pis>> t;

public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        int n = foods.size();
        for (int i = 0; i < n; ++i) {
            string a = foods[i], b = cuisines[i];
            int c = ratings[i];
            mp[a] = pis(c, b);
            t[b].insert(pis(-c, a));
        }
    }

    void changeRating(string food, int newRating) {
        pis& p = mp[food];
        t[p.second].erase(pis(-p.first, food));
        p.first = newRating;
        t[p.second].insert(pis(-p.first, food));
    }

    string highestRated(string cuisine) {
        return t[cuisine].begin()->second;
    }
};

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings* obj = new FoodRatings(foods, cuisines, ratings);
 * obj->changeRating(food,newRating);
 * string param_2 = obj->highestRated(cuisine);
 */