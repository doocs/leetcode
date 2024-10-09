class MyCalendarTwo {
    #OVERLAPS = 2;
    #cnt: Record<number, number> = {};

    book(start: number, end: number): boolean {
        this.#cnt[start] = (this.#cnt[start] ?? 0) + 1;
        this.#cnt[end] = (this.#cnt[end] ?? 0) - 1;

        let sum = 0;
        for (const v of Object.values(this.#cnt)) {
            sum += v;
            if (sum > this.#OVERLAPS) {
                this.#cnt[start]--;
                this.#cnt[end]++;

                if (!this.#cnt[start]) delete this.#cnt[start];
                if (!this.#cnt[end]) delete this.#cnt[end];

                return false;
            }
        }

        return true;
    }
}
