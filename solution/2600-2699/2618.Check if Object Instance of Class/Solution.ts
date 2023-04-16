function checkIfInstanceOf(obj: any, classFunction: any): boolean {
    if (classFunction == null) {
        return false;
    }
    while (obj != null) {
        const proto = Object.getPrototypeOf(obj);
        if (proto === classFunction.prototype) {
            return true;
        }
        obj = proto;
    }
    return false;
}

/**
 * checkIfInstanceOf(new Date(), Date); // true
 */
