---
comments: true
difficulty: Easy
tags:
    - Recursion
    - Linked List
---

<!-- problem:start -->

# [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists)

[中文文档](/solution/0000-0099/0021.Merge%20Two%20Sorted%20Lists/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp đầu của hai danh sách liên kết đã được sắp xếp <code>list1</code> và <code>list2</code>.</p>

<p>Gộp hai danh sách thành một danh sách <strong>đã sắp xếp</strong>. Danh sách này phải được tạo bằng cách nối các node của hai danh sách đầu tiên.</p>

<p>Trả về <em>đầu của danh sách liên kết đã gộp</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0021.Merge%20Two%20Sorted%20Lists/images/merge_ex1.jpg" style="width: 662px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> list1 = [1,2,4], list2 = [1,3,4]
<strong>Đầu ra:</strong> [1,1,2,3,4,4]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> list1 = [], list2 = []
<strong>Đầu ra:</strong> []
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> list1 = [], list2 = [0]
<strong>Đầu ra:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số node trong cả hai danh sách nằm trong phạm vi <code>[0, 50]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>Cả <code>list1</code> và <code>list2</code> đều được sắp xếp theo thứ tự <strong>không giảm</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đưa cả hai danh sách vào một mảng, sắp xếp rồi xây dựng lại. Tổng độ dài tối đa là $100$, nên cách này vẫn vượt qua được, nhưng nó bỏ qua thực tế rằng cả hai danh sách vốn đã được sắp xếp.
>
> Nút thắt là việc sắp xếp lại không cần thiết: mỗi bước chỉ cần lấy node nhỏ hơn trong hai node đầu. Sau khi lấy nó, phần còn lại vẫn là hai danh sách đã sắp xếp, tức là cùng một bài toán nhưng nhỏ hơn một node.
>
> So sánh các node đầu của $l_1$ và $l_2$; giữ node nhỏ hơn và nối phần gộp của phần còn lại vào thuộc tính $\textit{next}$ của nó. Khi một danh sách rỗng, danh sách còn lại chính là đáp án.
>
> Độ sâu đệ quy bằng số node. Độ dài ở đây rất nhỏ, nên không gian ngăn xếp $O(m+n)$ là phù hợp.

<!-- thinking:end -->

Đầu tiên, chúng ta kiểm tra xem các danh sách liên kết $l_1$ và $l_2$ có rỗng hay không. Nếu một trong hai danh sách rỗng, chúng ta trả về danh sách liên kết còn lại. Nếu không, chúng ta so sánh các node đầu của $l_1$ và $l_2$:

- Nếu giá trị của node đầu của $l_1$ nhỏ hơn hoặc bằng giá trị của node đầu của $l_2$, chúng ta gọi đệ quy hàm $mergeTwoLists(l_1.next, l_2)$, nối node đầu của $l_1$ với node đầu của danh sách liên kết được trả về, rồi trả về node đầu của $l_1$.
- Nếu không, chúng ta gọi đệ quy hàm $mergeTwoLists(l_1, l_2.next)$, nối node đầu của $l_2$ với node đầu của danh sách liên kết được trả về, rồi trả về node đầu của $l_2$.

Độ phức tạp thời gian là $O(m + n)$, và độ phức tạp không gian là $O(m + n)$. Ở đây, $m$ và $n$ lần lượt là độ dài của hai danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(
        self, list1: Optional[ListNode], list2: Optional[ListNode]
    ) -> Optional[ListNode]:
        if list1 is None or list2 is None:
            return list1 or list2
        if list1.val <= list2.val:
            list1.next = self.mergeTwoLists(list1.next, list2)
            return list1
        else:
            list2.next = self.mergeTwoLists(list1, list2.next)
            return list2
```

#### Java

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

#### C++

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        if (!list1) return list2;
        if (!list2) return list1;
        if (list1->val <= list2->val) {
            list1->next = mergeTwoLists(list1->next, list2);
            return list1;
        } else {
            list2->next = mergeTwoLists(list1, list2->next);
            return list2;
        }
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	if list1 == nil {
		return list2
	}
	if list2 == nil {
		return list1
	}
	if list1.Val <= list2.Val {
		list1.Next = mergeTwoLists(list1.Next, list2)
		return list1
	} else {
		list2.Next = mergeTwoLists(list1, list2.Next)
		return list2
	}
}
```

#### TypeScript

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function mergeTwoLists(list1: ListNode | null, list2: ListNode | null): ListNode | null {
    if (list1 == null || list2 == null) {
        return list1 || list2;
    }
    if (list1.val < list2.val) {
        list1.next = mergeTwoLists(list1.next, list2);
        return list1;
    } else {
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}
```

#### Rust

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn merge_two_lists(
        list1: Option<Box<ListNode>>,
        list2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        match (list1, list2) {
            (None, None) => None,
            (Some(list), None) => Some(list),
            (None, Some(list)) => Some(list),
            (Some(mut list1), Some(mut list2)) => {
                if list1.val < list2.val {
                    list1.next = Self::merge_two_lists(list1.next, Some(list2));
                    Some(list1)
                } else {
                    list2.next = Self::merge_two_lists(Some(list1), list2.next);
                    Some(list2)
                }
            }
        }
    }
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} list1
 * @param {ListNode} list2
 * @return {ListNode}
 */
var mergeTwoLists = function (list1, list2) {
    if (!list1 || !list2) {
        return list1 || list2;
    }
    if (list1.val <= list2.val) {
        list1.next = mergeTwoLists(list1.next, list2);
        return list1;
    } else {
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
};
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode MergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = MergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = MergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

#### Ruby

```rb
# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} list1
# @param {ListNode} list2
# @return {ListNode}
def merge_two_lists(list1, list2)
    if list1.nil?
        return list2
    end
    if list2.nil?
        return list1
    end
    if list1.val <= list2.val
        list1.next = merge_two_lists(list1.next, list2)
        return list1
    else
        list2.next = merge_two_lists(list1, list2.next)
        return list2
    end
end
```

#### PHP

```php
/**
 * Definition for a singly-linked list.
 * class ListNode {
 *     public $val = 0;
 *     public $next = null;
 *     function __construct($val = 0, $next = null) {
 *         $this->val = $val;
 *         $this->next = $next;
 *     }
 * }
 */
class Solution {
    /**
     * @param ListNode $list1
     * @param ListNode $list2
     * @return ListNode
     */
    function mergeTwoLists($list1, $list2) {
        if (is_null($list1)) {
            return $list2;
        }
        if (is_null($list2)) {
            return $list1;
        }
        if ($list1->val <= $list2->val) {
            $list1->next = $this->mergeTwoLists($list1->next, $list2);
            return $list1;
        } else {
            $list2->next = $this->mergeTwoLists($list1, $list2->next);
            return $list2;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Lặp

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đúng, nhưng ngăn xếp đệ quy tuyến tính theo số node. Các danh sách ở đây ngắn, tuy nhiên vẫn có thể thực hiện lựa chọn tương tự với không gian bổ sung hằng số.
>
> Nhận xét vẫn không thay đổi: mỗi bước vẫn so sánh hai node đầu và thêm node nhỏ hơn. Một node giả $dummy$ cùng con trỏ đuôi $curr$ thực hiện việc đó trong một vòng lặp; khi một danh sách hết, nối phần còn lại của danh sách kia vào.
>
> Thời gian tương đương phiên bản đệ quy; không gian bổ sung giảm xuống còn $O(1)$.

<!-- thinking:end -->

Chúng ta cũng có thể sử dụng phép lặp để thực hiện việc gộp hai danh sách liên kết đã sắp xếp.

Đầu tiên, chúng ta định nghĩa một node đầu giả $dummy$, sau đó lặp qua hai danh sách liên kết, so sánh các node đầu của hai danh sách liên kết, thêm node nhỏ hơn vào cuối của $dummy$, cho đến khi một trong hai danh sách liên kết rỗng, rồi thêm phần còn lại của danh sách liên kết kia vào cuối của $dummy$.

Cuối cùng, trả về $dummy.next$.

Độ phức tạp thời gian là $O(m + n)$, trong đó $m$ và $n$ lần lượt là độ dài của hai danh sách liên kết. Bỏ qua phần bộ nhớ mà danh sách liên kết kết quả sử dụng, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(
        self, list1: Optional[ListNode], list2: Optional[ListNode]
    ) -> Optional[ListNode]:
        dummy = ListNode()
        curr = dummy
        while list1 and list2:
            if list1.val <= list2.val:
                curr.next = list1
                list1 = list1.next
            else:
                curr.next = list2
                list2 = list2.next
            curr = curr.next
        curr.next = list1 or list2
        return dummy.next
```

#### Java

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
```

#### C++

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode* dummy = new ListNode();
        ListNode* curr = dummy;
        while (list1 && list2) {
            if (list1->val <= list2->val) {
                curr->next = list1;
                list1 = list1->next;
            } else {
                curr->next = list2;
                list2 = list2->next;
            }
            curr = curr->next;
        }
        curr->next = list1 ? list1 : list2;
        return dummy->next;
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	dummy := &ListNode{}
	curr := dummy
	for list1 != nil && list2 != nil {
		if list1.Val <= list2.Val {
			curr.Next = list1
			list1 = list1.Next
		} else {
			curr.Next = list2
			list2 = list2.Next
		}
		curr = curr.Next
	}
	if list1 != nil {
		curr.Next = list1
	} else {
		curr.Next = list2
	}
	return dummy.Next
}
```

#### TypeScript

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function mergeTwoLists(list1: ListNode | null, list2: ListNode | null): ListNode | null {
    const dummy = new ListNode(0);
    let cur = dummy;
    while (list1 != null && list2 != null) {
        if (list1.val < list2.val) {
            cur.next = list1;
            list1 = list1.next;
        } else {
            cur.next = list2;
            list2 = list2.next;
        }
        cur = cur.next;
    }
    cur.next = list1 || list2;
    return dummy.next;
}
```

#### Rust

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn merge_two_lists(
        mut list1: Option<Box<ListNode>>,
        mut list2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut new_list = ListNode::new(0);
        let mut cur = &mut new_list;
        while list1.is_some() && list2.is_some() {
            let (l1, l2) = (list1.as_deref_mut().unwrap(), list2.as_deref_mut().unwrap());
            if l1.val < l2.val {
                let next = l1.next.take();
                cur.next = list1.take();
                list1 = next;
            } else {
                let next = l2.next.take();
                cur.next = list2.take();
                list2 = next;
            }
            cur = cur.next.as_deref_mut().unwrap();
        }
        cur.next = list1.or(list2);
        new_list.next
    }
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} list1
 * @param {ListNode} list2
 * @return {ListNode}
 */
var mergeTwoLists = function (list1, list2) {
    const dummy = new ListNode();
    let curr = dummy;
    while (list1 && list2) {
        if (list1.val <= list2.val) {
            curr.next = list1;
            list1 = list1.next;
        } else {
            curr.next = list2;
            list2 = list2.next;
        }
        curr = curr.next;
    }
    curr.next = list1 || list2;
    return dummy.next;
};
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode MergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
```

#### Ruby

```rb
# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} list1
# @param {ListNode} list2
# @return {ListNode}
def merge_two_lists(list1, list2)
    dummy = ListNode.new()
    cur = dummy
    while list1 && list2
        if list1.val <= list2.val
            cur.next = list1
            list1 = list1.next
        else
            cur.next = list2
            list2 = list2.next
        end
        cur = cur.next
    end
    cur.next = list1 || list2
    dummy.next
end
```

#### PHP

```php
# Definition for singly-linked list.
# class ListNode {
#     public $val;
#     public $next;
#     public function __construct($val = 0, $next = null)
#     {
#         $this->val = $val;
#         $this->next = $next;
#     }
# }
class Solution {
    /**
     * @param ListNode $list1
     * @param ListNode $list2
     * @return ListNode
     */
    function mergeTwoLists($list1, $list2) {
        $dummy = new ListNode(0);
        $current = $dummy;
        while ($list1 != null && $list2 != null) {
            if ($list1->val <= $list2->val) {
                $current->next = $list1;
                $list1 = $list1->next;
            } else {
                $current->next = $list2;
                $list2 = $list2->next;
            }
            $current = $current->next;
        }
        if ($list1 != null) {
            $current->next = $list1;
        } elseif ($list2 != null) {
            $current->next = $list2;
        }
        return $dummy->next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
