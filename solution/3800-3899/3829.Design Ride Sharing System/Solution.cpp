class RideSharingSystem {
private:
    int t;
    set<pair<int, int>> riders;
    set<pair<int, int>> drivers;
    unordered_map<int, int> d;

public:
    RideSharingSystem() {
        t = 0;
    }

    void addRider(int riderId) {
        d[riderId] = t;
        riders.insert({t, riderId});
        t++;
    }

    void addDriver(int driverId) {
        drivers.insert({t, driverId});
        t++;
    }

    vector<int> matchDriverWithRider() {
        if (riders.empty() || drivers.empty()) {
            return {-1, -1};
        }
        int driverId = drivers.begin()->second;
        int riderId = riders.begin()->second;
        drivers.erase(drivers.begin());
        riders.erase(riders.begin());
        return {driverId, riderId};
    }

    void cancelRider(int riderId) {
        auto it = d.find(riderId);
        if (it != d.end()) {
            riders.erase({it->second, riderId});
        }
    }
};

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * RideSharingSystem* obj = new RideSharingSystem();
 * obj->addRider(riderId);
 * obj->addDriver(driverId);
 * vector<int> param_3 = obj->matchDriverWithRider();
 * obj->cancelRider(riderId);
 */
