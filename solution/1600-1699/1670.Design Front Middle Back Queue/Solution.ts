class FrontMiddleBackQueue {
    private q1: Deque<number>;
    private q2: Deque<number>;

    constructor() {
        this.q1 = new Deque<number>();
        this.q2 = new Deque<number>();
    }

    pushFront(val: number): void {
        this.q1.pushFront(val);
        this.rebalance();
    }

    pushMiddle(val: number): void {
        this.q1.pushBack(val);
        this.rebalance();
    }

    pushBack(val: number): void {
        this.q2.pushBack(val);
        this.rebalance();
    }

    popFront(): number {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q1.isEmpty() ? this.q2.popFront() : this.q1.popFront();
        this.rebalance();
        return val!;
    }

    popMiddle(): number {
        if (this.q1.isEmpty() && this.q2.isEmpty()) {
            return -1;
        }
        const val =
            this.q1.getSize() === this.q2.getSize() ? this.q1.popBack() : this.q2.popFront();
        this.rebalance();
        return val!;
    }

    popBack(): number {
        if (this.q2.isEmpty()) {
            return -1;
        }
        const val = this.q2.popBack();
        this.rebalance();
        return val!;
    }

    private rebalance(): void {
        if (this.q1.getSize() > this.q2.getSize()) {
            this.q2.pushFront(this.q1.popBack()!);
        }
        if (this.q2.getSize() > this.q1.getSize() + 1) {
            this.q1.pushBack(this.q2.popFront()!);
        }
    }
}

class Node<T> {
    value: T;
    next: Node<T> | null;
    prev: Node<T> | null;

    constructor(value: T) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class Deque<T> {
    private front: Node<T> | null;
    private back: Node<T> | null;
    private size: number;

    constructor() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    pushFront(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.next = this.front;
            this.front!.prev = newNode;
            this.front = newNode;
        }
        this.size++;
    }

    pushBack(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.prev = this.back;
            this.back!.next = newNode;
            this.back = newNode;
        }
        this.size++;
    }

    popFront(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.front!.value;
        this.front = this.front!.next;
        if (this.front !== null) {
            this.front.prev = null;
        } else {
            this.back = null;
        }
        this.size--;
        return value;
    }

    popBack(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.back!.value;
        this.back = this.back!.prev;
        if (this.back !== null) {
            this.back.next = null;
        } else {
            this.front = null;
        }
        this.size--;
        return value;
    }

    frontValue(): T | undefined {
        return this.front?.value;
    }

    backValue(): T | undefined {
        return this.back?.value;
    }

    getSize(): number {
        return this.size;
    }

    isEmpty(): boolean {
        return this.size === 0;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * var obj = new FrontMiddleBackQueue()
 * obj.pushFront(val)
 * obj.pushMiddle(val)
 * obj.pushBack(val)
 * var param_4 = obj.popFront()
 * var param_5 = obj.popMiddle()
 * var param_6 = obj.popBack()
 */
