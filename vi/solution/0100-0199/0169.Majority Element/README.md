---
comments: true
difficulty: Easy
tags:
    - Array
    - Hash Table
    - Divide and Conquer
    - Counting
    - Sorting
    - Boyer-Moore Voting
---

<!-- problem:start -->

# [169. Majority Element](https://leetcode.com/problems/majority-element)

[中文文档](/solution/0100-0199/0169.Majority%20Element/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng <code>nums</code> có kích thước <code>n</code>, hãy trả về <em>phần tử chiếm đa số</em>.</p>

<p>Phần tử chiếm đa số là phần tử xuất hiện nhiều hơn <code>&lfloor;n / 2&rfloor;</code> lần. Có thể giả sử rằng phần tử chiếm đa số luôn tồn tại trong mảng.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [3,2,3]
<strong>Đầu ra:</strong> 3
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [2,2,1,1,1,2,2]
<strong>Đầu ra:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>Đầu vào được tạo sao cho một phần tử chiếm đa số sẽ tồn tại trong mảng.</li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán trong thời gian tuyến tính và với không gian <code>O(1)</code> không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thuật toán bỏ phiếu Moore

<!-- thinking:start -->

> **Tư duy**
>
> Giá trị chiếm đa số xuất hiện nhiều hơn $\lfloor n/2\rfloor$ lần và được đảm bảo tồn tại. Đếm hoặc sắp xếp đều có thể thực hiện; câu hỏi mở rộng yêu cầu thời gian $O(n)$ và không gian $O(1)$. $n\le 5\times 10^4$.
>
> Boyer–Moore triệt tiêu các giá trị khác nhau theo từng cặp. Khi bộ đếm về 0, chúng ta thay đổi ứng viên. Phần tử chiếm đa số không thể bị triệt tiêu hoàn toàn, vì vậy ứng viên sau một lượt duyệt là đáp án; không cần lượt duyệt thứ hai.

<!-- thinking:end -->

Các bước cơ bản của thuật toán bỏ phiếu Moore như sau:

Khởi tạo phần tử $m$ và bộ đếm $cnt = 0$. Sau đó, với mỗi phần tử $x$ trong danh sách đầu vào:

1. Nếu $cnt = 0$, thì $m = x$ và $cnt = 1$;
1. Nếu không, nếu $m = x$, thì $cnt = cnt + 1$; ngược lại, $cnt = cnt - 1$.

Nhìn chung, thuật toán bỏ phiếu Moore cần **hai lượt duyệt** qua danh sách đầu vào. Ở lượt duyệt đầu tiên, chúng ta tạo giá trị ứng viên $m$; nếu tồn tại phần tử chiếm đa số, giá trị ứng viên sẽ là giá trị của phần tử đó. Ở lượt duyệt thứ hai, chúng ta chỉ cần tính tần suất của giá trị ứng viên để xác nhận đó có phải là phần tử chiếm đa số hay không. Vì đề bài này đã nêu rõ rằng phần tử chiếm đa số tồn tại, chúng ta có thể trả về trực tiếp $m$ sau lượt duyệt đầu tiên mà không cần lượt duyệt thứ hai để xác nhận.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng $nums$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = m = 0
        for x in nums:
            if cnt == 0:
                m, cnt = x, 1
            else:
                cnt += 1 if m == x else -1
        return m
```

#### Java

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int x : nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, m = 0;
        for (int& x : nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
};
```

#### Go

```go
func majorityElement(nums []int) int {
	var cnt, m int
	for _, x := range nums {
		if cnt == 0 {
			m, cnt = x, 1
		} else {
			if m == x {
				cnt++
			} else {
				cnt--
			}
		}
	}
	return m
}
```

#### TypeScript

```ts
function majorityElement(nums: number[]): number {
    let cnt: number = 0;
    let m: number = 0;
    for (const x of nums) {
        if (cnt === 0) {
            m = x;
            cnt = 1;
        } else {
            cnt += m === x ? 1 : -1;
        }
    }
    return m;
}
```

#### Rust

```rust
impl Solution {
    pub fn majority_element(nums: Vec<i32>) -> i32 {
        let mut m = 0;
        let mut cnt = 0;
        for &x in nums.iter() {
            if cnt == 0 {
                m = x;
                cnt = 1;
            } else {
                cnt += if m == x { 1 } else { -1 };
            }
        }
        m
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let m = 0;
    for (const x of nums) {
        if (cnt === 0) {
            m = x;
            cnt = 1;
        } else {
            cnt += m === x ? 1 : -1;
        }
    }
    return m;
};
```

#### C#

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int x in nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function majorityElement($nums) {
        $m = 0;
        $cnt = 0;
        foreach ($nums as $x) {
            if ($cnt == 0) {
                $m = $x;
            }
            if ($m == $x) {
                $cnt++;
            } else {
                $cnt--;
            }
        }
        return $m;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
