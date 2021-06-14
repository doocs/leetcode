var buf = '';

process.stdin.on('readable', function () {
    var chunk = process.stdin.read();
    if (chunk) buf += chunk.toString();
});

let getInputArgs = line => {
    return line.split(' ').filter(s => s !== '').map(x => parseInt(x));
}

function mergeSort(nums, low, high) {
    if (low >= high) {
        return;
    }
    
    const mid = (low + high) >> 1;
    mergeSort(nums, low, mid);
    mergeSort(nums, mid + 1, high);
    let i = low;
    let j = mid + 1;
    let tmp = [];
    while (i <= mid && j <= high) {
        if (nums[i] <= nums[j]) {
            tmp.push(nums[i++]);
        } else {
            tmp.push(nums[j++]);
        }
    }
    while (i <= mid) {
        tmp.push(nums[i++]);
    }
    while (j <= high) {
        tmp.push(nums[j++]);
    }
    for (i = low, j = 0; i <= high; ++i, ++j) {
        nums[i] = tmp[j];
    }
}



process.stdin.on('end', function () {
    buf.split('\n').forEach(function (line, lineIdx) {
        if (lineIdx % 2 === 1) {
            nums = getInputArgs(line);
            mergeSort(nums, 0, nums.length - 1);
            console.log(nums.join(' '));
        }

    });
});