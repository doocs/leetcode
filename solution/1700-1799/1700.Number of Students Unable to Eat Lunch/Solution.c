int countStudents(int* students, int studentsSize, int* sandwiches, int sandwichesSize) {
    int count[2] = {0};
    for (int i = 0; i < studentsSize; i++) {
        count[students[i]]++;
    }
    for (int i = 0; i < sandwichesSize; i++) {
        int j = sandwiches[i];
        if (count[j] == 0) {
            return count[j ^ 1];
        }
        count[j]--;
    }
    return 0;
}
