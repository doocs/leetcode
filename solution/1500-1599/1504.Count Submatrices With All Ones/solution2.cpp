struct Tuple {
    int entry;
    int rowIdx;
    int cumulatedCount;
};

class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int rows = mat.size();
        int cols = mat[0].size();

        vector<vector<int>> table(rows, vector<int>(cols, 0));

        // `stacks[j]`: each jth column's remaining tuples:
        // {table entry, row idx, cumulated count until [i][j]}.
        vector<stack<Tuple>> stacks(cols);

        int submatricesCount = 0;

        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
            for (int colIdx = 0; colIdx < cols; colIdx++) {
                if (mat[rowIdx][colIdx] == 1) {
                    table[rowIdx][colIdx] = 1;
                    if (colIdx > 0)
                        table[rowIdx][colIdx] += table[rowIdx][colIdx - 1];
                }

                int currentEntry = table[rowIdx][colIdx];

                while (!stacks[colIdx].empty() && stacks[colIdx].top().entry >= currentEntry)
                    stacks[colIdx].pop();

                int cumulatedCount = 0;
                int prevRowIdx = -1;

                if (!stacks[colIdx].empty()) {
                    prevRowIdx = stacks[colIdx].top().rowIdx; // Previous barrier.
                    cumulatedCount += stacks[colIdx].top().cumulatedCount; // Inheritance.
                }

                cumulatedCount += currentEntry * (rowIdx - prevRowIdx);
                submatricesCount += cumulatedCount;

                stacks[colIdx].push({currentEntry, rowIdx, cumulatedCount});
            }
        }

        return submatricesCount;
    }
};