String.prototype.replicate = function (times) {
    return Array(times).fill(this).join('');
};
