---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
    - Binary Search
---

<!-- problem:start -->

# [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)

[中文文档](/solution/0100-0199/0167.Two%20Sum%20II%20-%20Input%20Array%20Is%20Sorted/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên được <strong>đánh chỉ số từ 1</strong> <code>numbers</code> đã được <strong><em>sắp xếp theo thứ tự không giảm</em></strong>, hãy tìm hai số sao cho tổng của chúng bằng một số <code>target</code> cho trước. Gọi hai số này là <code>numbers[index<sub>1</sub>]</code> và <code>numbers[index<sub>2</sub>]</code>, trong đó <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers.length</code>.</p>

<p><em>Trả về các chỉ số của hai số </em><code>index<sub>1</sub></code><em> và </em><code>index<sub>2</sub></code><em>, <strong>mỗi chỉ số được tăng thêm một đơn vị</strong>, dưới dạng một mảng số nguyên </em><code>[index<sub>1</sub>, index<sub>2</sub>]</code><em> có độ dài 2.</em></p>

<p>Các bộ kiểm thử được tạo sao cho có <strong>chính xác một nghiệm</strong>. Bạn <strong>không được</strong> sử dụng cùng một phần tử hai lần.</p>

<p>Lời giải của bạn chỉ được sử dụng bộ nhớ bổ sung hằng số.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> numbers = [<u>2</u>,<u>7</u>,11,15], target = 9
<strong>Đầu ra:</strong> [1,2]
<strong>Giải thích:</strong> Tổng của 2 và 7 là 9. Do đó, index<sub>1</sub> = 1, index<sub>2</sub> = 2. Chúng ta trả về [1, 2].
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> numbers = [<u>2</u>,3,<u>4</u>], target = 6
<strong>Đầu ra:</strong> [1,3]
<strong>Giải thích:</strong> Tổng của 2 và 4 là 6. Do đó, index<sub>1</sub> = 1, index<sub>2</sub> = 3. Chúng ta trả về [1, 3].
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> numbers = [<u>-1</u>,<u>0</u>], target = -1
<strong>Đầu ra:</strong> [1,2]
<strong>Giải thích:</strong> Tổng của -1 và 0 là -1. Do đó, index<sub>1</sub> = 1, index<sub>2</sub> = 2. Chúng ta trả về [1, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> được sắp xếp theo <strong>thứ tự không giảm</strong>.</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>Các bộ kiểm thử được tạo sao cho có <strong>chính xác một nghiệm</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Bài toán Two Sum trên một mảng đã sắp xếp, với các chỉ số bắt đầu từ 1 và chính xác một cặp. Một bảng băm sử dụng $O(n)$ bộ nhớ. Với mỗi $numbers[i]$, hãy tìm kiếm nhị phân $target-numbers[i]$ ở phía bên phải. Vì $n\le 3\times 10^4$, độ phức tạp thời gian là $O(n\log n)$ và độ phức tạp không gian là $O(1)$.

<!-- thinking:end -->

Vì mảng được sắp xếp theo thứ tự không giảm, nên với mỗi `numbers[i]`, chúng ta có thể tìm vị trí của `target - numbers[i]` bằng tìm kiếm nhị phân, rồi trả về $[i + 1, j + 1]$ nếu tồn tại.

Độ phức tạp thời gian là $O(n \times \log n)$, trong đó $n$ là độ dài của mảng `numbers`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        n = len(numbers)
        for i in range(n - 1):
            x = target - numbers[i]
            j = bisect_left(numbers, x, lo=i + 1)
            if j < n and numbers[j] == x:
                return [i + 1, j + 1]
```

#### Java

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length;; ++i) {
            int x = target - numbers[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (numbers[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (numbers[l] == x) {
                return new int[] {i + 1, l + 1};
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, n = numbers.size();; ++i) {
            int x = target - numbers[i];
            int j = lower_bound(numbers.begin() + i + 1, numbers.end(), x) - numbers.begin();
            if (j < n && numbers[j] == x) {
                return {i + 1, j + 1};
            }
        }
    }
};
```

#### Go

```go
func twoSum(numbers []int, target int) []int {
	for i, n := 0, len(numbers); ; i++ {
		x := target - numbers[i]
		j := sort.SearchInts(numbers[i+1:], x) + i + 1
		if j < n && numbers[j] == x {
			return []int{i + 1, j + 1}
		}
	}
}
```

#### TypeScript

```ts
function twoSum(numbers: number[], target: number): number[] {
    const n = numbers.length;
    for (let i = 0; ; ++i) {
        const x = target - numbers[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (numbers[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (numbers[l] === x) {
            return [i + 1, l + 1];
        }
    }
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        let mut l = 0;
        let mut r = n - 1;
        loop {
            match (numbers[l] + numbers[r]).cmp(&target) {
                Ordering::Less => {
                    l += 1;
                }
                Ordering::Greater => {
                    r -= 1;
                }
                Ordering::Equal => {
                    break;
                }
            }
        }
        vec![(l as i32) + 1, (r as i32) + 1]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    const n = numbers.length;
    for (let i = 0; ; ++i) {
        const x = target - numbers[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (numbers[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (numbers[l] === x) {
            return [i + 1, l + 1];
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 thực hiện tìm kiếm nhị phân tại mỗi chỉ số trái. Dùng hai con trỏ từ hai đầu: nếu tổng nhỏ hơn, dịch con trỏ trái sang phải; nếu tổng lớn hơn, dịch con trỏ phải sang trái. Thứ tự đã sắp xếp không thể bỏ sót cặp duy nhất, và thời gian giảm còn $O(n)$.

<!-- thinking:end -->

Chúng ta xác định hai con trỏ $i$ và $j$, lần lượt trỏ đến phần tử đầu tiên và phần tử cuối cùng của mảng. Mỗi lần, chúng ta tính $numbers[i] + numbers[j]$. Nếu tổng bằng giá trị target, trả về trực tiếp $[i + 1, j + 1]$. Nếu tổng nhỏ hơn giá trị target, dịch $i$ sang phải một vị trí; nếu tổng lớn hơn giá trị target, dịch $j$ sang trái một vị trí.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng `numbers`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 0, len(numbers) - 1
        while i < j:
            x = numbers[i] + numbers[j]
            if x == target:
                return [i + 1, j + 1]
            if x < target:
                i += 1
            else:
                j -= 1
```

#### Java

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1;;) {
            int x = numbers[i] + numbers[j];
            if (x == target) {
                return new int[] {i + 1, j + 1};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, j = numbers.size() - 1;;) {
            int x = numbers[i] + numbers[j];
            if (x == target) {
                return {i + 1, j + 1};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
    }
};
```

#### Go

```go
func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		x := numbers[i] + numbers[j]
		if x == target {
			return []int{i + 1, j + 1}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
}
```

#### TypeScript

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let i = 0, j = numbers.length - 1; ;) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i + 1, j + 1];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    for (let i = 0, j = numbers.length - 1; ;) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i + 1, j + 1];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
