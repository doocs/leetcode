#include <stdio.h>
#include <stdlib.h>
#define TABLE_SIZE 2048
typedef struct {
    int key;
    int value;
    int used;
} Entry;
int hash(int key) {
    return abs(key) % TABLE_SIZE;
}
void insert(Entry* table, int key, int value) {
    int idx = hash(key);
    while (table[idx].used) {
        idx = (idx + 1) % TABLE_SIZE;
    }
    table[idx].key = key;
    table[idx].value = value;
    table[idx].used = 1;
}
int contains(Entry* table, int key, int* out_value) {
    int idx = hash(key);
    int start = idx;
    while (table[idx].used) {
        if (table[idx].key == key) {
            *out_value = table[idx].value;
            return 1;
        }
        idx = (idx + 1) % TABLE_SIZE;
        if (idx == start) break;
    }
    return 0;
}
int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    Entry* map = (Entry*) calloc(TABLE_SIZE, sizeof(Entry));
    int* result = (int*) malloc(2 * sizeof(int));
    for (int i = 0; i < numsSize; ++i) {
        int x = nums[i];
        int y = target - x;
        int foundIndex;
        if (contains(map, y, &foundIndex)) {
            result[0] = foundIndex;
            result[1] = i;
            *returnSize = 2;
            free(map);
            return result;
        }
        insert(map, x, i);
    }
    *returnSize = 0;
    free(map);
    free(result);
    return NULL;
}
