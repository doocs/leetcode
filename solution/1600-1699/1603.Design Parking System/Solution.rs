struct ParkingSystem {
    cnt: [i32; 4],
}

impl ParkingSystem {
    fn new(big: i32, medium: i32, small: i32) -> Self {
        ParkingSystem {
            cnt: [0, big, medium, small],
        }
    }

    fn add_car(&mut self, car_type: i32) -> bool {
        if self.cnt[car_type as usize] == 0 {
            return false;
        }
        self.cnt[car_type as usize] -= 1;
        true
    }
}
