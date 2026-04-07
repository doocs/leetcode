class EventManager {
public:
    set<pair<int, int>> sl;
    unordered_map<int, int> d;

    EventManager(vector<vector<int>>& events) {
        for (auto& e : events) {
            int eventId = e[0], priority = e[1];
            sl.insert({-priority, eventId});
            d[eventId] = priority;
        }
    }

    void updatePriority(int eventId, int newPriority) {
        int old = d[eventId];
        sl.erase({-old, eventId});
        sl.insert({-newPriority, eventId});
        d[eventId] = newPriority;
    }

    int pollHighest() {
        if (sl.empty()) {
            return -1;
        }
        auto it = sl.begin();
        int eventId = it->second;
        sl.erase(it);
        d.erase(eventId);
        return eventId;
    }
};

/**
 * Your EventManager object will be instantiated and called as such:
 * EventManager* obj = new EventManager(events);
 * obj->updatePriority(eventId,newPriority);
 * int param_2 = obj->pollHighest();
 */
