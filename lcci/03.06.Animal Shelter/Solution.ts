class AnimalShelf {
    private cats: number[];
    private dogs: number[];

    constructor() {
        this.cats = [];
        this.dogs = [];
    }

    enqueue(animal: number[]): void {
        const [i, j] = animal;
        this[j === 0 ? 'cats' : 'dogs'].push(i);
    }

    dequeueAny(): number[] {
        const n = this.dogs.length;
        const m = this.cats.length;
        if (n === 0 && m === 0) {
            return [-1, -1];
        }
        if ((this.dogs[0] ?? Infinity) < (this.cats[0] ?? Infinity)) {
            return [this.dogs.shift(), 1];
        } else {
            return [this.cats.shift(), 0];
        }
    }

    dequeueDog(): number[] {
        if (this.dogs.length === 0) {
            return [-1, -1];
        }
        return [this.dogs.shift(), 1];
    }

    dequeueCat(): number[] {
        if (this.cats.length === 0) {
            return [-1, -1];
        }
        return [this.cats.shift(), 0];
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * var obj = new AnimalShelf()
 * obj.enqueue(animal)
 * var param_2 = obj.dequeueAny()
 * var param_3 = obj.dequeueDog()
 * var param_4 = obj.dequeueCat()
 */
