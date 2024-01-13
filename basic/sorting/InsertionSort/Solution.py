def insertion_sort(array):
    for i in range(len(array)):
        cur_index = i
        while array[cur_index - 1] > array[cur_index] and cur_index - 1 >= 0:
            array[cur_index], array[cur_index - 1] = (
                array[cur_index - 1],
                array[cur_index],
            )
            cur_index -= 1
    return array


array = [10, 17, 50, 7, 30, 24, 27, 45, 15, 5, 36, 21]
print(insertion_sort(array))
