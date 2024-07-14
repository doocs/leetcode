class AnimalShelf {
    private var q: [[Int]] = Array(repeating: [], count: 2)

    init() {
    }

    func enqueue(_ animal: [Int]) {
        q[animal[1]].append(animal[0])
    }

    func dequeueAny() -> [Int] {
        if q[0].isEmpty || (!q[1].isEmpty && q[1].first! < q[0].first!) {
            return dequeueDog()
        }
        return dequeueCat()
    }

    func dequeueDog() -> [Int] {
        return q[1].isEmpty ? [-1, -1] : [q[1].removeFirst(), 1]
    }

    func dequeueCat() -> [Int] {
        return q[0].isEmpty ? [-1, -1] : [q[0].removeFirst(), 0]
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * let obj = new AnimalShelf();
 * obj.enqueue(animal);
 * let param_2 = obj.dequeueAny();
 * let param_3 = obj.dequeueDog();
 * let param_4 = obj.dequeueCat();
 */
 