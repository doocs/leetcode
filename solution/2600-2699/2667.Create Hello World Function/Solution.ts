function createHelloWorld() {
    return function (...args): string {
        return 'Hello World';
    };
}

/**
 * const f = createHelloWorld();
 * f(); // "Hello World"
 */
