# 插入排序

先来看一个问题。一个有序的数组，我们往里面添加一个新的数据后，如何继续保持数据有序呢？很简单，我们只要遍历数组，找到数据应该插入的位置将其插入即可。

这是一个动态排序的过程，即动态地往有序集合中添加数据，我们可以通过这种方法保持集合中的数据一直有序。而对于一组静态数据，我们也可以借鉴上面讲的插入方法，来进行排序，于是就有了插入排序算法。

那么插入排序具体是如何借助上面的思想来实现排序的呢？

首先，我们将数组中的数据分为两个区间，**已排序区间**和**未排序区间**。初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。

与冒泡排序对比：

-   在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的。
-   在插入排序中，经过每一轮的排序处理后，数组前端的数是排好序的。

## 代码示例

<!-- tabs:start -->

### **Java**

```java
import java.util.Arrays;

public class InsertionSort {

    private static void insertionSort(int[] nums) {
        for (int i = 1, j, n = nums.length; i < n; ++i) {
            int num = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > num; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = num;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

### **JavaScript**

```js
function insertionSort(inputArr) {
    let len = inputArr.length;
    for (let i = 1; i <= len - 1; i++) {
        let temp = inputArr[i];
        let j = i - 1;
        while (j >= 0 && inputArr[j] > temp) {
            inputArr[j + 1] = inputArr[j];
            j--;
        }
        inputArr[j + 1] = temp;
    }
    return inputArr;
}

let arr = [6, 3, 2, 1, 5];
console.log(insertionSort(arr));
```

### **Go**

```go
package main

import "fmt"

func insertionSort(nums []int) {
	for i, n := 1, len(nums); i < n; i++ {
		j, num := i-1, nums[i]
		for ; j >= 0 && nums[j] > num; j-- {
			nums[j+1] = nums[j]
		}
		nums[j+1] = num
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	insertionSort(nums)
	fmt.Println(nums)
}
```

### **C++**

```cpp
#include <iostream>
#include <vector>

using namespace std;

void printvec(const vector<int>& vec, const string& strbegin = "", const string& strend = "") {
    cout << strbegin << endl;
    for (auto val : vec) {
        cout << val << "\t";
    }

    cout << endl;
    cout << strend << endl;
}

void insertsort(vector<int>& vec) {
    for (int i = 1; i < vec.size(); i++) {
        int j = i - 1;
        int num = vec[i];
        for (; j >= 0 && vec[j] > num; j--) {
            vec[j + 1] = vec[j];
        }

        vec[j + 1] = num;
    }

    return;
}

int main() {
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    printvec(vec);
    insertsort(vec);
    printvec(vec, "after insert sort");
    return (0);
}
```

### **Rust**

```rust
fn insertion_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 1..n {
        let mut j = i - 1;
        let temp = nums[i];
        while j >= 0 as usize && nums[j] > temp {
            nums[j + 1] = nums[j];
            j -= 1;
        }
        nums[j + 1] = temp;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    insertion_sort(&mut nums);
    println!("{:?}", nums);
}
```

### **C#**

```cs
using System.Diagnostics;
using static System.Console;
namespace Pro;
public class Program
{
    public static void Main()
    {
        int[] test = new int[] { 31, 12, 10, 5, 6, 7, 8, 10, 23, 34, 56, 43, 32, 21 };
        InsertSortNums(test);
        foreach (var item in test)
        {
            WriteLine(item);
        }
    }
    public static void InsertSortNums(int[] nums)
    {
        for (int initial = 1; initial < nums.Length; initial++)
        {
            for (int second_sort = 0; second_sort < initial; second_sort++)
            {
                if (nums[second_sort] > nums[initial])
                {
                    swap(ref nums[second_sort], ref nums[initial]);
                }
            }
        }
    }

    private static void swap(ref int compare_left, ref int compare_right)
    {
        int temp = compare_left;
        compare_left = compare_right;
        compare_right = temp;
    }
}
```

### **Python3**

```python
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
```

<!-- tabs:end -->

## 算法分析

空间复杂度 $O(1)$，时间复杂度 $O(n^2)$。

分情况讨论：

1. 给定的数组按照顺序排好序：只需要进行 $n-1$ 次比较，两两交换次数为 0，时间复杂度为 $O(n)$，这是最好的情况。
1. 给定的数组按照逆序排列：需要进行 $\frac{n\times (n-1)}{2}$ 次比较，时间复杂度为 $O(n^2)$，这是最坏的情况。
1. 给定的数组杂乱无章：在这种情况下，平均时间复杂度是 $O(n^2)$。

因此，时间复杂度是 $O(n^2)$，这也是一种稳定的排序算法。
