impl Solution {
    #[allow(dead_code)]
    pub fn find_in_mountain_array(target: i32, mountain_arr: &MountainArray) -> i32 {
        let n = mountain_arr.length();

        // First find the maximum element in the array
        let mut l = 0;
        let mut r = n - 1;

        while l < r {
            let mid = (l + r) >> 1;
            if mountain_arr.get(mid) > mountain_arr.get(mid + 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        let left = Self::binary_search(mountain_arr, 0, l, 1, target);

        if left == -1 {
            Self::binary_search(mountain_arr, l, n - 1, -1, target)
        } else {
            left
        }
    }

    #[allow(dead_code)]
    fn binary_search(m: &MountainArray, mut l: i32, mut r: i32, k: i32, target: i32) -> i32 {
        let n = m.length();

        while l < r {
            let mid = (l + r) >> 1;
            if k * m.get(mid) >= k * target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if m.get(l) == target {
            l
        } else {
            -1
        }
    }
}