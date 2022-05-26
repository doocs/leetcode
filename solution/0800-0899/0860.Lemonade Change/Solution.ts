function lemonadeChange(bills: number[]): boolean {
    let five = 0;
    let ten = 0;
    for (let bill of bills) {
        switch (bill) {
            case 5:
                five++;
                break;
            case 10:
                five--;
                ten++;
                break;
            case 20:
                if (ten !== 0) {
                    ten -= 1;
                    bill -= 10;
                }
                five -= bill / 5 - 1;
                break;
        }

        if (five < 0) {
            return false;
        }
    }
    return true;
}
