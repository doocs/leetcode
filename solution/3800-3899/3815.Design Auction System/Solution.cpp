class AuctionSystem {
    unordered_map<int, set<pair<int, int>>> items;
    unordered_map<int, unordered_map<int, int>> users;

public:
    AuctionSystem() {
    }

    void addBid(int userId, int itemId, int bidAmount) {
        if (users[userId].count(itemId)) {
            removeBid(userId, itemId);
        }
        users[userId][itemId] = bidAmount;
        items[itemId].insert({bidAmount, userId});
    }

    void updateBid(int userId, int itemId, int newAmount) {
        int oldAmount = users[userId][itemId];
        auto& s = items[itemId];
        s.erase({oldAmount, userId});
        s.insert({newAmount, userId});
        users[userId][itemId] = newAmount;
    }

    void removeBid(int userId, int itemId) {
        int oldAmount = users[userId][itemId];
        auto& s = items[itemId];
        s.erase({oldAmount, userId});
        users[userId].erase(itemId);
    }

    int getHighestBidder(int itemId) {
        auto it = items.find(itemId);
        if (it == items.end() || it->second.empty()) {
            return -1;
        }
        return it->second.rbegin()->second;
    }
};

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem* obj = new AuctionSystem();
 * obj->addBid(userId,itemId,bidAmount);
 * obj->updateBid(userId,itemId,newAmount);
 * obj->removeBid(userId,itemId);
 * int param_4 = obj->getHighestBidder(itemId);
 */
