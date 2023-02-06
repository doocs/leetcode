/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** mergeSimilarItems(int** items1, int items1Size, int* items1ColSize, int** items2, int items2Size,
    int* items2ColSize, int* returnSize, int** returnColumnSizes) {
    int count[1001] = {0};
    for (int i = 0; i < items1Size; i++) {
        count[items1[i][0]] += items1[i][1];
    }
    for (int i = 0; i < items2Size; i++) {
        count[items2[i][0]] += items2[i][1];
    }
    int** ans = malloc(sizeof(int*) * (items1Size + items2Size));
    *returnColumnSizes = malloc(sizeof(int) * (items1Size + items2Size));
    int size = 0;
    for (int i = 0; i < 1001; i++) {
        if (count[i]) {
            ans[size] = malloc(sizeof(int) * 2);
            ans[size][0] = i;
            ans[size][1] = count[i];
            (*returnColumnSizes)[size] = 2;
            size++;
        }
    }
    *returnSize = size;
    return ans;
}
