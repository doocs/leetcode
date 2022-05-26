impl Solution {
    fn create_time(bit_arr: &[bool; 10]) -> (i32, i32) {
        let mut h = 0;
        let mut m = 0;
        for i in 0..4 {
            h <<= 1;
            h |= if bit_arr[i] { 1 } else { 0 };
        }
        for i in 4..10 {
            m <<= 1;
            m |= if bit_arr[i] { 1 } else { 0 };
        }

        (h, m)
    }

    fn helper(res: &mut Vec<String>, bit_arr: &mut [bool; 10], i: usize, count: usize) {
        if i + count > 10 || count == 0 {
            return;
        }
        bit_arr[i] = true;
        if count == 1 {
            let (h, m) = Self::create_time(bit_arr);
            if h < 12 && m < 60 {
                if m < 10 {
                    res.push(format!("{}:0{}", h, m));
                } else {
                    res.push(format!("{}:{}", h, m));
                }
            }
        }
        Self::helper(res, bit_arr, i + 1, count - 1);
        bit_arr[i] = false;
        Self::helper(res, bit_arr, i + 1, count);
    }

    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        if turned_on == 0 {
            return vec![String::from("0:00")];
        }
        let mut res = vec![];
        let mut bit_arr = [false; 10];
        Self::helper(&mut res, &mut bit_arr, 0, turned_on as usize);
        res
    }
}
