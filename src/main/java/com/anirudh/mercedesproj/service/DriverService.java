package com.anirudh.mercedesproj.service;

import com.anirudh.mercedesproj.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {
    public Integer solveDisparity(Driver dr) {
        int n = dr.getN();
        List<Integer> s = dr.getS();
        int result=0;
        int output=Integer.MAX_VALUE;

        int[] arr = s.stream().mapToInt(Integer::intValue).toArray();

        int[] arr1= new int[s.size()];

        //Get All combinations for the array
        List<List<Integer>> allCombinations = getAllCombinations(arr);

        for (List<Integer> list : allCombinations){
            for(int i=0;i<arr1.length;i++) {
                arr1[i] = list.get(i);
                int max = Arrays.stream(arr1).max().getAsInt();
                int min = Arrays.stream(arr1).min().getAsInt();
                result = result + (max - min);
            }
            output = Math.min(output,result);
        }

        return output;
    }

    private List<List<Integer>> getAllCombinations(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        getAllCombinationsHelper(arr, new ArrayList<Integer>(), result);
        return result;
    }

    private void getAllCombinationsHelper(int[] arr, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!current.contains(arr[i])) {
                current.add(arr[i]);
                getAllCombinationsHelper(arr, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
