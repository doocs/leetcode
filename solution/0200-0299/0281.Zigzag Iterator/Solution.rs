struct ZigzagIterator {
    v1: Vec<i32>,
    v2: Vec<i32>,
    /// `false` represents `v1`, `true` represents `v2`
    flag: bool,
}

impl ZigzagIterator {
    fn new(v1: Vec<i32>, v2: Vec<i32>) -> Self {
        Self {
            v1,
            v2,
            // Initially beginning with `v1`
            flag: false,
        }
    }

    fn next(&mut self) -> i32 {
        if !self.flag {
            // v1
            if self.v1.is_empty() && !self.v2.is_empty() {
                self.flag = true;
                let ret = self.v2.remove(0);
                return ret;
            }
            if self.v2.is_empty() {
                let ret = self.v1.remove(0);
                return ret;
            }
            let ret = self.v1.remove(0);
            self.flag = true;
            return ret;
        } else {
            // v2
            if self.v2.is_empty() && !self.v1.is_empty() {
                self.flag = false;
                let ret = self.v1.remove(0);
                return ret;
            }
            if self.v1.is_empty() {
                let ret = self.v2.remove(0);
                return ret;
            }
            let ret = self.v2.remove(0);
            self.flag = false;
            return ret;
        }
    }

    fn has_next(&self) -> bool {
        !self.v1.is_empty() || !self.v2.is_empty()
    }
}
