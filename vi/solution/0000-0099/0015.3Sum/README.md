---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [15. 3Sum](https://leetcode.com/problems/3sum)

[中文文档](/solution/0000-0099/0015.3Sum/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên nums, hãy trả về tất cả các bộ ba <code>[nums[i], nums[j], nums[k]]</code> sao cho <code>i != j</code>, <code>i != k</code> và <code>j != k</code>, đồng thời <code>nums[i] + nums[j] + nums[k] == 0</code>.</p>

<p>Chú ý rằng tập nghiệm không được chứa các bộ ba trùng lặp.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-1,0,1,2,-1,-4]
<strong>Đầu ra:</strong> [[-1,-1,2],[-1,0,1]]
<strong>Giải thích:</strong> 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
Các bộ ba phân biệt là [-1,0,1] và [-1,-1,2].
Lưu ý rằng thứ tự của đầu ra và thứ tự của các bộ ba không quan trọng.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,1,1]
<strong>Đầu ra:</strong> []
<strong>Giải thích:</strong> Bộ ba duy nhất có thể có không có tổng bằng 0.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,0,0]
<strong>Đầu ra:</strong> [[0,0,0]]
<strong>Giải thích:</strong> Bộ ba duy nhất có thể có có tổng bằng 0.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng ba vòng lặp lồng nhau cùng một set để xử lý tính duy nhất. Cách này đúng, nhưng có độ phức tạp $O(n^3)$. Với $n\le 3000$, nó sẽ không vượt qua được các test. Dùng hashing cho bài toán two-sum với mỗi $i$ đạt $O(n^2)$, nhưng việc xử lý các phần tử trùng lặp và không gian bổ sung trở nên phức tạp.
>
> Nút thắt là tìm hai số có tổng bằng giá trị đối của một giá trị cố định mà không lặp lại các bộ ba. Trước tiên, hãy sắp xếp để các phần tử trùng lặp nằm cạnh nhau và dễ bỏ qua; two-sum trên mảng đã sắp xếp có thể dùng hai con trỏ trong $O(n)$. Nếu $nums[i]>0$, mọi phần tử sau nó đều dương, nên tổng không thể bằng $0$ nữa.
>
> Vì vậy, chúng ta sắp xếp, liệt kê số đầu tiên, rồi thu hẹp phần còn lại bằng hai con trỏ.

<!-- thinking:end -->

Chúng ta nhận thấy rằng đề bài không yêu cầu trả về bộ ba theo thứ tự, vì vậy trước hết có thể sắp xếp mảng, nhờ đó dễ dàng bỏ qua các phần tử trùng lặp.

Tiếp theo, chúng ta liệt kê phần tử đầu tiên của bộ ba $nums[i]$, trong đó $0 \leq i \lt n - 2$. Với mỗi $i$, chúng ta có thể tìm $j$ và $k$ thỏa mãn $nums[i] + nums[j] + nums[k] = 0$ bằng cách duy trì hai con trỏ $j = i + 1$ và $k = n - 1$. Trong quá trình liệt kê, chúng ta cần bỏ qua các phần tử trùng lặp để tránh các bộ ba trùng lặp.

Logic kiểm tra cụ thể như sau:

Nếu $i \gt 0$ và $nums[i] = nums[i - 1]$, điều đó có nghĩa là phần tử đang được liệt kê giống với phần tử trước đó, chúng ta có thể bỏ qua trực tiếp, vì nó sẽ không tạo ra kết quả mới.

Nếu $nums[i] \gt 0$, điều đó có nghĩa là phần tử đang được liệt kê lớn hơn $0$, nên tổng của ba số chắc chắn không thể bằng $0$, và việc liệt kê kết thúc.

Ngược lại, chúng ta đặt con trỏ trái $j = i + 1$ và con trỏ phải $k = n - 1$. Khi $j \lt k$, vòng lặp được thực hiện; tổng của ba số $x = nums[i] + nums[j] + nums[k]$ được tính và so sánh với $0$:

- Nếu $x \lt 0$, điều đó có nghĩa là $nums[j]$ quá nhỏ, chúng ta cần di chuyển $j$ sang phải.
- Nếu $x \gt 0$, điều đó có nghĩa là $nums[k]$ quá lớn, chúng ta cần di chuyển $k$ sang trái.
- Nếu không, điều đó có nghĩa là chúng ta đã tìm thấy một bộ ba hợp lệ, thêm nó vào đáp án, di chuyển $j$ sang phải, di chuyển $k$ sang trái và bỏ qua tất cả các phần tử trùng lặp để tiếp tục tìm bộ ba hợp lệ tiếp theo.

Sau khi kết thúc việc liệt kê, chúng ta nhận được đáp án gồm các bộ ba.

Độ phức tạp thời gian là $O(n^2)$, và độ phức tạp không gian là $O(\log n)$. $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        n = len(nums)
        ans = []
        for i in range(n - 2):
            if nums[i] > 0:
                break
            if i and nums[i] == nums[i - 1]:
                continue
            j, k = i + 1, n - 1
            while j < k:
                x = nums[i] + nums[j] + nums[k]
                if x < 0:
                    j += 1
                elif x > 0:
                    k -= 1
                else:
                    ans.append([nums[i], nums[j], nums[k]])
                    j, k = j + 1, k - 1
                    while j < k and nums[j] == nums[j - 1]:
                        j += 1
                    while j < k and nums[k] == nums[k + 1]:
                        k -= 1
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < 0) {
                    ++j;
                } else if (x > 0) {
                    --k;
                } else {
                    ans.add(List.of(nums[i], nums[j++], nums[k--]));
                    while (j < k && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        --k;
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        int n = nums.size();
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < 0) {
                    ++j;
                } else if (x > 0) {
                    --k;
                } else {
                    ans.push_back({nums[i], nums[j++], nums[k--]});
                    while (j < k && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        --k;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func threeSum(nums []int) (ans [][]int) {
	sort.Ints(nums)
	n := len(nums)
	for i := 0; i < n-2 && nums[i] <= 0; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		j, k := i+1, n-1
		for j < k {
			x := nums[i] + nums[j] + nums[k]
			if x < 0 {
				j++
			} else if x > 0 {
				k--
			} else {
				ans = append(ans, []int{nums[i], nums[j], nums[k]})
				j, k = j+1, k-1
				for j < k && nums[j] == nums[j-1] {
					j++
				}
				for j < k && nums[k] == nums[k+1] {
					k--
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function threeSum(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    const n = nums.length;
    for (let i = 0; i < n - 2 && nums[i] <= 0; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const x = nums[i] + nums[j] + nums[k];
            if (x < 0) {
                ++j;
            } else if (x > 0) {
                --k;
            } else {
                ans.push([nums[i], nums[j++], nums[k--]]);
                while (j < k && nums[j] === nums[j - 1]) {
                    ++j;
                }
                while (j < k && nums[k] === nums[k + 1]) {
                    --k;
                }
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn three_sum(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let n = nums.len();
        let mut res = vec![];
        let mut i = 0;
        while i < n - 2 && nums[i] <= 0 {
            let mut l = i + 1;
            let mut r = n - 1;
            while l < r {
                match (nums[i] + nums[l] + nums[r]).cmp(&0) {
                    Ordering::Less => {
                        l += 1;
                    }
                    Ordering::Greater => {
                        r -= 1;
                    }
                    Ordering::Equal => {
                        res.push(vec![nums[i], nums[l], nums[r]]);
                        l += 1;
                        r -= 1;
                        while l < n && nums[l] == nums[l - 1] {
                            l += 1;
                        }
                        while r > 0 && nums[r] == nums[r + 1] {
                            r -= 1;
                        }
                    }
                }
            }
            i += 1;
            while i < n - 2 && nums[i] == nums[i - 1] {
                i += 1;
            }
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var threeSum = function (nums) {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    const ans = [];
    for (let i = 0; i < n - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const x = nums[i] + nums[j] + nums[k];
            if (x < 0) {
                ++j;
            } else if (x > 0) {
                --k;
            } else {
                ans.push([nums[i], nums[j++], nums[k--]]);
                while (j < k && nums[j] === nums[j - 1]) {
                    ++j;
                }
                while (j < k && nums[k] === nums[k + 1]) {
                    --k;
                }
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> ThreeSum(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        IList<IList<int>> ans = new List<IList<int>>();
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < 0) {
                    ++j;
                } else if (x > 0) {
                    --k;
                } else {
                    ans.Add(new List<int> { nums[i], nums[j--], nums[k--] });
                    while (j < k && nums[j] == nums[j + 1]) {
                        ++j;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        --k;
                    }
                }
            }
        }
        return ans;
    }
}
```

#### Ruby

```rb
# @param {Integer[]} nums
# @return {Integer[][]}
def three_sum(nums)
  res = []
  nums.sort!

  for i in 0..(nums.length - 3)
    next if i > 0 && nums[i - 1] == nums[i]
    j = i + 1
    k = nums.length - 1
    while j < k do
      sum = nums[i] + nums[j] + nums[k]
      if sum < 0
        j += 1
      elsif sum > 0
        k -= 1
      else
        res += [[nums[i], nums[j], nums[k]]]
        j += 1
        k -= 1
        j += 1 while nums[j] == nums[j - 1]
        k -= 1 while nums[k] == nums[k + 1]
      end
    end
  end

  res
end
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[][]
     */
    function threeSum($nums) {
        sort($nums);
        $ans = [];
        $n = count($nums);
        for ($i = 0; $i < $n - 2 && $nums[$i] <= 0; ++$i) {
            if ($i > 0 && $nums[$i] == $nums[$i - 1]) {
                continue;
            }
            $j = $i + 1;
            $k = $n - 1;
            while ($j < $k) {
                $x = $nums[$i] + $nums[$j] + $nums[$k];
                if ($x < 0) {
                    ++$j;
                } elseif ($x > 0) {
                    --$k;
                } else {
                    $ans[] = [$nums[$i], $nums[$j++], $nums[$k--]];
                    while ($j < $k && $nums[$j] == $nums[$j - 1]) {
                        ++$j;
                    }
                    while ($j < $k && $nums[$k] == $nums[$k + 1]) {
                        --$k;
                    }
                }
            }
        }
        return $ans;
    }
}
```

#### C

```c
int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int** threeSum(int* nums, int numsSize, int* returnSize, int** returnColumnSizes) {
    *returnSize = 0;
    int cap = 1000;
    int** ans = (int**) malloc(sizeof(int*) * cap);
    *returnColumnSizes = (int*) malloc(sizeof(int) * cap);

    qsort(nums, numsSize, sizeof(int), cmp);

    for (int i = 0; i < numsSize - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum < 0) {
                ++j;
            } else if (sum > 0) {
                --k;
            } else {
                if (*returnSize >= cap) {
                    cap *= 2;
                    ans = (int**) realloc(ans, sizeof(int*) * cap);
                    *returnColumnSizes = (int*) realloc(*returnColumnSizes, sizeof(int) * cap);
                }
                ans[*returnSize] = (int*) malloc(sizeof(int) * 3);
                ans[*returnSize][0] = nums[i];
                ans[*returnSize][1] = nums[j];
                ans[*returnSize][2] = nums[k];
                (*returnColumnSizes)[*returnSize] = 3;
                (*returnSize)++;

                ++j;
                --k;
                while (j < k && nums[j] == nums[j - 1]) ++j;
                while (j < k && nums[k] == nums[k + 1]) --k;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
