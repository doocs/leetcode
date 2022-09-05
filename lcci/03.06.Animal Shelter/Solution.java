class AnimalShelf {
    Queue<Integer> cats;
    Queue<Integer> dogs;
    public AnimalShelf() {
        cats = new LinkedList<>();
        dogs = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            cats.offer(animal[0]);
        } else {
            dogs.offer(animal[0]);
        }
    }

    public int[] dequeueAny() {
        return dogs.isEmpty()
            ? dequeueCat()
            : (cats.isEmpty() ? dequeueDog()
                              : (dogs.peek() < cats.peek() ? dequeueDog() : dequeueCat()));
    }

    public int[] dequeueDog() {
        return dogs.isEmpty() ? new int[] {-1, -1} : new int[] {dogs.poll(), 1};
    }

    public int[] dequeueCat() {
        return cats.isEmpty() ? new int[] {-1, -1} : new int[] {cats.poll(), 0};
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf obj = new AnimalShelf();
 * obj.enqueue(animal);
 * int[] param_2 = obj.dequeueAny();
 * int[] param_3 = obj.dequeueDog();
 * int[] param_4 = obj.dequeueCat();
 */