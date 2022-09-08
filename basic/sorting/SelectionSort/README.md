# 选择排序

选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。

## 代码示例

<!-- tabs:start -->

### **Java**

```java
import java.util.Arrays;

public class SelectionSort {

    private static void selectionSort(int[] nums) {
        for (int i = 0, n = nums.length; i < n - 1; ++i) {
            int minIndex = i;
            for (int j = i; j < n; ++j) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

### **JavaScript**

```js
function selectionSort(inputArr) {
    let len = inputArr.length;
    for (let i = 0; i <= len - 2; i++) {
        let j = i;
        let min = j;
        while (j <= len - 1) {
            if (inputArr[j] < inputArr[min]) min = j;
            j++;
        }
        let temp = inputArr[i];
        inputArr[i] = inputArr[min];
        inputArr[min] = temp;
    }
    return inputArr;
}

let arr = [6, 3, 2, 1, 5];
console.log(selectionSort(arr));
```

### **Go**

```go
package main

import "fmt"

func selectionSort(nums []int) {
	for i, n := 0, len(nums); i < n-1; i++ {
		minIndex := i
		for j := i + 1; j < n; j++ {
			if nums[j] < nums[minIndex] {
				minIndex = j
			}
		}
		nums[minIndex], nums[i] = nums[i], nums[minIndex]
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	selectionSort(nums)
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

void selectsort(vector<int>& vec) {
    for (int i = 0; i < vec.size() - 1; i++) {
        int minidx = i;
        for (int j = i + 1; j < vec.size(); j++) {
            if (vec[minidx] > vec[j]) {
                minidx = j;
            }
        }

        swap(vec[i], vec[minidx]);
    }
}

int main(void) {
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    printvec(vec);
    selectsort(vec);
    printvec(vec, "after insert sort");
    return (0);
}
```

### **Rust**

```rust
fn selection_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 0..n - 1 {
        let mut min_index = i;
        for j in i..n {
            if nums[j] < nums[min_index] {
                min_index = j;
            }
        }
        let temp = nums[min_index];
        nums[min_index] = nums[i];
        nums[i] = temp;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    selection_sort(&mut nums);
    println!("{:?}", nums);
}
```

### **C#**

```cs
using static System.Console;
namespace Pro;
public class Program
{
    public static void Main()
    {
        int[] test = new int[] {90, 12, 77, 9, 0, 2, 23, 23, 3, 57, 80};
        SelectionSortNums(test);
        foreach (var item in test)
        {
            WriteLine(item);
        }
    }
    public static void SelectionSortNums(int[] nums)
    {
        for (int initial = 0; initial < nums.Length; initial++)
        {
            for (int second_sort = initial; second_sort < nums.Length; second_sort++)
            {
                if (nums[initial] > nums[second_sort])
                {
                    swap(ref nums[initial], ref nums[second_sort]);
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
def selection_sort(arr):
    n = len(arr)
    for i in range(n - 1):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[min_index], arr[i] = arr[i], arr[min_index]


arr = [26, 11, 99, 33, 69, 77, 55, 56, 67]
selection_sort(arr)
print(arr)
```

<!-- tabs:end -->

## 算法分析

空间复杂度 $O(1)$，时间复杂度 $O(n^2)$。

那选择排序是稳定的排序算法吗？

答案是否定的，**选择排序是一种不稳定的排序算法**。选择排序每次都要找剩余未排序元素中的最小值，并和前面的元素交换位置，这样破坏了稳定性。

比如 5，8，5，2，9 这样一组数据，使用选择排序算法来排序的话，第一次找到最小元素 2，与第一个 5 交换位置，那第一个 5 和中间的 5 顺序就变了，所以就不稳定了。正是因此，相对于冒泡排序和插入排序，选择排序就稍微逊色了。
