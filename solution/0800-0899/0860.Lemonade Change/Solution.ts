function lemonadeChange(bills: number[]): boolean {
    let [five, ten] = [0, 0];
    for (const x of bills) {
        switch (x) {
            case 5:
                five++;
                break;
            case 10:
                five--;
                ten++;
                break;
            case 20:
                if (ten) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
                break;
        }

        if (five < 0) {
            return false;
        }
    }
    return true;
}
