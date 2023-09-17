impl Solution {
    pub fn can_place_flowers(flowerbed: Vec<i32>, n: i32) -> bool {
        let (mut flowers, mut cnt) = (vec![0], 0);
        flowers.append(&mut flowerbed.clone());
        flowers.push(0);

        for i in 1..flowers.len() - 1 {
            let (l, r) = (flowers[i - 1], flowers[i + 1]);
            if l + flowers[i] + r == 0 {
                flowers[i] = 1;
                cnt += 1;
            }
        }
        cnt >= n
    }
}
