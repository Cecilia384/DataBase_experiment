package com.zpy.controller;

import com.zpy.pojo.CountNumber;
import com.zpy.pojo.MainMenu;
import com.zpy.pojo.MainMenu1;
import com.zpy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("main")
public class MainMenuController {
    @Autowired
    private ProductService productService;


    //查询不同种类商品的销售量
    @RequestMapping("mainMenu")
    public List<MainMenu>list(){

        List<CountNumber> list = productService.queryNum();
        List<MainMenu>countList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setType(list.get(i).getName());
            mainMenu.setMount(Integer.valueOf(list.get(i).getCount()));
            countList.add(mainMenu);
        }

//        Collections.sort(countList,(o1,o2)->{
//            if (o1.getMount()>o2.getMount()){
//                return -1;
//            }else if (o1.getMount()<o2.getMount()){
//                return 1;
//            }else {
//                return 0;
//            }
//        });

        if (countList.size()<12){
            return countList;
        }

        List<MainMenu>arrList=new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            arrList.add(countList.get(i));
        }
        return arrList;
    }



    //查询不同商品的销售额
    @RequestMapping("mainMenu2")
    public List<MainMenu1>list1(){

        List<CountNumber> list = productService.queryTotal();
        List<MainMenu1>countList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MainMenu1 mainMenu1 = new MainMenu1();
            mainMenu1.setType(list.get(i).getName());
            double v = Double.parseDouble(list.get(i).getCount());
            mainMenu1.setMount(v);
            countList.add(mainMenu1);
        }


        if (countList.size()<12){
            return countList;
        }

        List<MainMenu1>arrList=new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            arrList.add(countList.get(i));
        }
        return arrList;
    }
}
