function swap(arr, i, j) {
    const temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

function heapify(arr, n, i) {
    if (i >= n) return;
    const c1 = 2 * i + 1;
    const c2 = 2 * i + 2;
    let largest = i;
    if (c1 < n && arr[c1] > arr[largest]) {
        largest = c1;
    }
    if (c2 < n && arr[c2] > arr[largest]) {
        largest = c2;
    }
    if (largest !== i) {
        swap(arr, i, largest);
        heapify(arr, n, largest);
    }
}

function buildHeap(arr, n) {
    const lastNode = n - 1;
    const lastParent = Math.floor((lastNode - 1) / 2);
    for (let i = lastParent; i >= 0; i--) {
        heapify(arr, n, i);
    }
}

function heapSort(arr, n) {
    buildHeap(arr, n);
    for (let i = n - 1; i >= 0; i--) {
        swap(arr, i, 0);
        heapify(arr, i, 0);
    }
}

function main(arr, n, m) {
    heapSort(arr, n);
    const list = arr.slice(0, m);
    console.log(list.join(' '));
}
