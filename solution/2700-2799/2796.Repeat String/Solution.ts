declare global {
    interface String {
        replicate(times: number): string;
    }
}

String.prototype.replicate = function (times: number) {
    return new Array(times).fill(this).join('');
};
