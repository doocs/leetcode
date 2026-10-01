---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [55. Jump Game](https://leetcode.com/problems/jump-game)

[中文文档](/solution/0000-0099/0055.Jump%20Game/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng số nguyên <code>nums</code>. Ban đầu, bạn đứng ở <strong>chỉ số đầu tiên</strong> của mảng, và mỗi phần tử trong mảng biểu thị độ dài bước nhảy tối đa tại vị trí đó.</p>

<p>Trả về <code>true</code><em> nếu bạn có thể đến được chỉ số cuối cùng, hoặc </em><code>false</code><em> nếu không thể</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,3,1,1,4]
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Nhảy 1 bước từ chỉ số 0 đến 1, sau đó nhảy 3 bước đến chỉ số cuối cùng.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,2,1,0,4]
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Dù thế nào, bạn cũng sẽ luôn đến chỉ số 3. Độ dài bước nhảy tối đa tại đó là 0, khiến bạn không thể đến được chỉ số cuối cùng.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tham lam

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng DFS/BFS trên các chỉ số có thể đến được, hoặc dùng DP để xác định liệu mỗi chỉ số có thể đến được hay không. Cả hai đều đúng, nhưng trong trường hợp xấu nhất có độ phức tạp $O(n^2)$. Với $n \le 10^4$, đây là một giới hạn chặt, và chúng ta chỉ quan tâm đến chỉ số cuối cùng chứ không phải đường đi.
>
> Điểm lãng phí nằm ở việc mở rộng mọi bước nhảy. Các chỉ số có thể đến được tạo thành một tiền tố: duy trì chỉ số xa nhất có thể đến được $mx$; nếu tồn tại một $i > mx$, chúng ta bị chặn lại.
>
> Vì vậy, chúng ta duyệt từ trái sang phải, cập nhật $mx$ bằng $i + \textit{nums}[i]$, và khi hoàn tất việc duyệt thì điều đó có nghĩa là có thể đến được cuối mảng.

<!-- thinking:end -->

Chúng ta sử dụng một biến $mx$ để duy trì chỉ số xa nhất hiện tại có thể đến được, ban đầu $mx = 0$.

Chúng ta duyệt mảng từ trái sang phải. Với mỗi vị trí $i$ mà chúng ta duyệt qua, nếu $mx < i$, điều đó có nghĩa là không thể đến được vị trí hiện tại, nên chúng ta trả về `false` ngay lập tức. Ngược lại, vị trí xa nhất có thể đến được bằng cách nhảy từ vị trí $i$ là $i+nums[i]$; chúng ta dùng $i+nums[i]$ để cập nhật giá trị của $mx$, tức là $mx = \max(mx, i + nums[i])$.

Khi kết thúc việc duyệt, chúng ta trả về `true` ngay lập tức.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

Các bài toán tương tự:

- [45. Jump Game II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0045.Jump%20Game%20II/README_EN.md)
- [1024. Video Stitching](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1024.Video%20Stitching/README_EN.md)
- [1326. Minimum Number of Taps to Open to Water a Garden](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        mx = 0
        for i, x in enumerate(nums):
            if mx < i:
                return False
            mx = max(mx, i + x)
        return True
```

#### Java

```java
class Solution {
    public boolean canJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (mx < i) {
                return false;
            }
            mx = Math.max(mx, i + nums[i]);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canJump(vector<int>& nums) {
        int mx = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (mx < i) {
                return false;
            }
            mx = max(mx, i + nums[i]);
        }
        return true;
    }
};
```

#### Go

```go
func canJump(nums []int) bool {
	mx := 0
	for i, x := range nums {
		if mx < i {
			return false
		}
		mx = max(mx, i+x)
	}
	return true
}
```

#### TypeScript

```ts
function canJump(nums: number[]): boolean {
    let mx: number = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (mx < i) {
            return false;
        }
        mx = Math.max(mx, i + nums[i]);
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn can_jump(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut mx = 0;

        for i in 0..n {
            if mx < i {
                return false;
            }
            mx = std::cmp::max(mx, i + (nums[i] as usize));
        }

        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function (nums) {
    let mx = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (mx < i) {
            return false;
        }
        mx = Math.max(mx, i + nums[i]);
    }
    return true;
};
```

#### C#

```cs
public class Solution {
    public bool CanJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.Length; ++i) {
            if (mx < i) {
                return false;
            }
            mx = Math.Max(mx, i + nums[i]);
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
