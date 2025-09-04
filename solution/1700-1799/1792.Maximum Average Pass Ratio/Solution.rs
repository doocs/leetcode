use std::cmp::Ordering;
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_average_ratio(classes: Vec<Vec<i32>>, extra_students: i32) -> f64 {
        struct Node {
            gain: f64,
            a: i32,
            b: i32,
        }

        impl PartialEq for Node {
            fn eq(&self, other: &Self) -> bool {
                self.gain == other.gain
            }
        }
        impl Eq for Node {}

        impl PartialOrd for Node {
            fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
                self.gain.partial_cmp(&other.gain)
            }
        }
        impl Ord for Node {
            fn cmp(&self, other: &Self) -> Ordering {
                self.partial_cmp(other).unwrap()
            }
        }

        fn calc_gain(a: i32, b: i32) -> f64 {
            (a + 1) as f64 / (b + 1) as f64 - a as f64 / b as f64
        }

        let n = classes.len() as f64;
        let mut pq = BinaryHeap::new();

        for c in classes {
            let a = c[0];
            let b = c[1];
            pq.push(Node {
                gain: calc_gain(a, b),
                a,
                b,
            });
        }

        let mut extra = extra_students;
        while extra > 0 {
            if let Some(mut node) = pq.pop() {
                node.a += 1;
                node.b += 1;
                node.gain = calc_gain(node.a, node.b);
                pq.push(node);
            }
            extra -= 1;
        }

        let mut sum = 0.0;
        while let Some(node) = pq.pop() {
            sum += node.a as f64 / node.b as f64;
        }

        sum / n
    }
}
