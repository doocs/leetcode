function join(arr1: ArrayType[], arr2: ArrayType[]): ArrayType[] {
    const r = (acc: Obj, x: ArrayType): Obj => ((acc[x.id] = x), acc);
    const d = arr1.reduce(r, {});

    arr2.forEach(x => {
        if (d[x.id]) {
            Object.assign(d[x.id], x);
        } else {
            d[x.id] = x;
        }
    });
    return Object.values(d);
}

type JSONValue = null | boolean | number | string | JSONValue[] | { [key: string]: JSONValue };
type ArrayType = { id: number } & Record<string, JSONValue>;

type Obj = Record<number, ArrayType>;
