class AnimalShelf {
    private q: number[][] = [[], []];
    constructor() {}

    enqueue(animal: number[]): void {
        const [i, j] = animal;
        this.q[j].push(i);
    }

    dequeueAny(): number[] {
        if (this.q[0].length === 0 || (this.q[1].length > 0 && this.q[0][0] > this.q[1][0])) {
            return this.dequeueDog();
        }
        return this.dequeueCat();
    }

    dequeueDog(): number[] {
        if (this.q[1].length === 0) {
            return [-1, -1];
        }
        return [this.q[1].shift()!, 1];
    }

    dequeueCat(): number[] {
        if (this.q[0].length === 0) {
            return [-1, -1];
        }
        return [this.q[0].shift()!, 0];
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
