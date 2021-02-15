public static void fastSort(int[] arr, int l, int r){
        if(l >= r) return;
        int low = l, high = r;
        int temp = arr[low];
        while(low < high){
            while(low < high && arr[high] >= temp) --high;
            arr[low] = arr[high];
            while(low < high && arr[low] <= temp) ++low;
            arr[high] = arr[low];
        }
        arr[low] = temp;
        fastSort(arr, l, low - 1);
        fastSort(arr, low + 1, r);
    }
