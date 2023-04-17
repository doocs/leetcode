type F = () => Promise<any>;

function promisePool(functions: F[], n: number): Promise<any> {
    const wrappers = functions.map(fn => async () => {
        await fn();
        const nxt = waiting.shift();
        nxt && (await nxt());
    });

    const running = wrappers.slice(0, n);
    const waiting = wrappers.slice(n);
    return Promise.all(running.map(fn => fn()));
}
