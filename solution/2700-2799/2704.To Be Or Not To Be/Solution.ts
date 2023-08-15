type ToBeOrNotToBe = {
    toBe: (val: any) => boolean;
    notToBe: (val: any) => boolean;
};

function expect(val: any): ToBeOrNotToBe {
    return {
        toBe: (toBeVal: any) => {
            if (val !== toBeVal) {
                throw new Error('Not Equal');
            }
            return true;
        },
        notToBe: (notToBeVal: any) => {
            if (val === notToBeVal) {
                throw new Error('Equal');
            }
            return true;
        },
    };
}

/**
 * expect(5).toBe(5); // true
 * expect(5).notToBe(5); // throws "Equal"
 */
