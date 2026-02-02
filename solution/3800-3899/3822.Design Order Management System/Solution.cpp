class OrderManagementSystem {
    using Key = pair<string, int>;

    struct KeyHash {
        size_t operator()(const Key& k) const {
            return hash<string>()(k.first) ^ (hash<int>()(k.second) << 1);
        }
    };

    unordered_map<int, string> orderTypeMap;
    unordered_map<int, int> priceMap;
    unordered_map<Key, vector<int>, KeyHash> t;

public:
    OrderManagementSystem() {}

    void addOrder(int orderId, string orderType, int price) {
        orderTypeMap[orderId] = orderType;
        priceMap[orderId] = price;
        t[{orderType, price}].push_back(orderId);
    }

    void modifyOrder(int orderId, int newPrice) {
        string orderType = orderTypeMap[orderId];
        int oldPrice = priceMap[orderId];
        priceMap[orderId] = newPrice;

        auto& oldList = t[{orderType, oldPrice}];
        oldList.erase(find(oldList.begin(), oldList.end(), orderId));

        t[{orderType, newPrice}].push_back(orderId);
    }

    void cancelOrder(int orderId) {
        string orderType = orderTypeMap[orderId];
        int price = priceMap[orderId];

        orderTypeMap.erase(orderId);
        priceMap.erase(orderId);

        auto& list = t[{orderType, price}];
        list.erase(find(list.begin(), list.end(), orderId));
    }

    vector<int> getOrdersAtPrice(string orderType, int price) {
        auto it = t.find({orderType, price});
        if (it == t.end()) return {};
        return it->second;
    }
};

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * OrderManagementSystem* obj = new OrderManagementSystem();
 * obj->addOrder(orderId,orderType,price);
 * obj->modifyOrder(orderId,newPrice);
 * obj->cancelOrder(orderId);
 * vector<int> param_4 = obj->getOrdersAtPrice(orderType,price);
 */
