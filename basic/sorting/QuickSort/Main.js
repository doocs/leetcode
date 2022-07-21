var buf = '';

process.stdin.on('readable', function () {
    var chunk = process.stdin.read();
    if (chunk) buf += chunk.toString();
});

let getInputArgs = line => {
    return line
        .split(' ')
        .filter(s => s !== '')
        .map(x => parseInt(x));
};

function quickSort(nums, left, right) {
    if (left >= right) {
        return;
    }

    let i = left - 1;
    let j = right + 1;
    let x = nums[(left + right) >> 1];
    while (i < j) {
        while (nums[++i] < x);
        while (nums[--j] > x);
        if (i < j) {
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    quickSort(nums, left, j);
    quickSort(nums, j + 1, right);
}

process.stdin.on('end', function () {
    buf.split('\n').forEach(function (line, lineIdx) {
        if (lineIdx % 2 === 1) {
            nums = getInputArgs(line);
            quickSort(nums, 0, nums.length - 1);
            console.log(nums.join(' '));
        }
    });
});
