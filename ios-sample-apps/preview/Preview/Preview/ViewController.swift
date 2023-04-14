//
//  ViewController.swift
//  Preview
//
//  Created by Mehmet Şahin Ayçiçek on 12.09.2022.
//

import UIKit
import Storyly


class ViewController: UIViewController, StorylyDelegate {
    let STORYLY_INSTANCE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTMxMTUsImluc19pZCI6MTQyNDh9.8_WHJ9WFClC2UCi3MVBc4B4m1Hfce-LHrA0SUcnJiVo"
    
    let userPropertiesData = [
          "user_id": "John",
          "country_count": "7",
          "mile_count": "2751"
        ]
    
    
    var userModal: UserModal?

    @IBOutlet weak var storylyView: StorylyView!
   
    @IBOutlet weak var setUserProperties: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.storylyView.storylyInit = StorylyInit(storylyId:STORYLY_INSTANCE_TOKEN)
        // Do any additional setup after loading the view.
        self.storylyView.rootViewController = self
        self.storylyView.delegate = self
        
        var userDict: [String: String] = [:]
        if userModal?.user_id != nil {
            userDict["user_id"] = userModal?.user_id
        }
        if userModal?.country_count != nil {
            userDict["country_count"] = userModal?.country_count
        }
        if  userModal?.mile_count != nil {
            userDict["mile_count"] = userModal?.mile_count
        }
        
        self.storylyView.storylyInit.userData = userDict
        
    }

}

