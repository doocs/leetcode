impl Solution {
    pub fn longest_repeating(
        s: String,
        query_characters: String,
        query_indices: Vec<i32>,
    ) -> Vec<i32> {
        struct Node {
            l: usize,
            r: usize,
            lmx: i32,
            rmx: i32,
            mx: i32,
        }

        struct SegmentTree {
            s: Vec<u8>,
            tr: Vec<Node>,
        }

        impl SegmentTree {
            fn new(s: String) -> Self {
                let n = s.len();
                let mut tree = Self {
                    s: s.into_bytes(),
                    tr: (0..n * 4 + 5)
                        .map(|_| Node {
                            l: 0,
                            r: 0,
                            lmx: 0,
                            rmx: 0,
                            mx: 0,
                        })
                        .collect(),
                };
                tree.build(1, 1, n);
                tree
            }

            fn build(&mut self, u: usize, l: usize, r: usize) {
                self.tr[u] = Node {
                    l,
                    r,
                    lmx: 1,
                    rmx: 1,
                    mx: 1,
                };

                if l == r {
                    return;
                }

                let mid = (l + r) >> 1;
                self.build(u << 1, l, mid);
                self.build(u << 1 | 1, mid + 1, r);
                self.pushup(u);
            }

            fn modify(&mut self, u: usize, x: usize, v: u8) {
                if self.tr[u].l == self.tr[u].r {
                    self.s[x - 1] = v;
                    return;
                }

                let mid = (self.tr[u].l + self.tr[u].r) >> 1;

                if x <= mid {
                    self.modify(u << 1, x, v);
                } else {
                    self.modify(u << 1 | 1, x, v);
                }

                self.pushup(u);
            }

            fn pushup(&mut self, u: usize) {
                let left = u << 1;
                let right = u << 1 | 1;

                let left_lmx = self.tr[left].lmx;
                let left_rmx = self.tr[left].rmx;
                let left_mx = self.tr[left].mx;
                let right_lmx = self.tr[right].lmx;
                let right_rmx = self.tr[right].rmx;
                let right_mx = self.tr[right].mx;

                self.tr[u].lmx = left_lmx;
                self.tr[u].rmx = right_rmx;
                self.tr[u].mx = left_mx.max(right_mx);

                let left_len = self.tr[left].r - self.tr[left].l + 1;
                let right_len = self.tr[right].r - self.tr[right].l + 1;

                if self.s[self.tr[left].r - 1] == self.s[self.tr[right].l - 1] {
                    if left_lmx as usize == left_len {
                        self.tr[u].lmx += right_lmx;
                    }

                    if right_rmx as usize == right_len {
                        self.tr[u].rmx += left_rmx;
                    }

                    self.tr[u].mx = self.tr[u].mx.max(left_rmx + right_lmx);
                }
            }

            fn query(&self) -> i32 {
                self.tr[1].mx
            }
        }

        let mut tree = SegmentTree::new(s);
        let mut ans = Vec::with_capacity(query_indices.len());

        for (x, v) in query_indices.iter().zip(query_characters.bytes()) {
            tree.modify(1, *x as usize + 1, v);
            ans.push(tree.query());
        }

        ans
    }
}
