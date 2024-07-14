class AnimalShelf {
public:
    AnimalShelf() {
    }

    void enqueue(vector<int> animal) {
        q[animal[1]].push(animal[0]);
    }

    vector<int> dequeueAny() {
        if (q[0].empty() || (!q[1].empty() && q[1].front() < q[0].front())) {
            return dequeueDog();
        }
        return dequeueCat();
    }

    vector<int> dequeueDog() {
        if (q[1].empty()) {
            return {-1, -1};
        }
        int dog = q[1].front();
        q[1].pop();
        return {dog, 1};
    }

    vector<int> dequeueCat() {
        if (q[0].empty()) {
            return {-1, -1};
        }
        int cat = q[0].front();
        q[0].pop();
        return {cat, 0};
    }

private:
    queue<int> q[2];
};

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf* obj = new AnimalShelf();
 * obj->enqueue(animal);
 * vector<int> param_2 = obj->dequeueAny();
 * vector<int> param_3 = obj->dequeueDog();
 * vector<int> param_4 = obj->dequeueCat();
 */