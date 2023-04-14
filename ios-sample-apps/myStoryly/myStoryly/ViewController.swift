//
//  ViewController.swift
//  myStoryly
//
//  Created by Mehmet Şahin Ayçiçek on 7.11.2022.
//

import UIKit
import Storyly

class ViewController: UIViewController {
    
    let STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTE1NDQsImluc19pZCI6MTIzNDF9.Ffue1gq3hBpLuiQ6FVseby94Lh0Y6PNLOK6suaHbnvo"
    
    let userPropertiesData = [
        "username" : "John",
        "gift_amount" : "35%",
        "cover_img" : "https://cdn.pixabay.com/photo/2022/10/15/21/23/cat-7523894_1280.jpg"
    ]
    
    @IBOutlet weak var storylyView: StorylyView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.storylyView.storylyInit = StorylyInit(
            storylyId: STORYLY_INSTANCE_TOKEN,
            segmentation: StorylySegmentation(segments: Set(arrayLiteral: "how-to", "introduction", "discover", "newuser" )),
            customParameter: "Gold Member"
        )
        self.storylyView.rootViewController = self
        // the class(indicated with self) extends StorylyDelegate
        self.storylyView.delegate = self // Override event functions
        // Do any additional setup after loading the view.
        self.storylyView.storylyInit.userData = userPropertiesData
        
        
    }

}

extension ViewController : StorylyDelegate {
    
    func storylyLoaded(_ storylyView: Storyly.StorylyView,
                       storyGroupList: [Storyly.StoryGroup],
                       dataSource: StorylyDataSource) {
        print("StorylyLoaded ===>\(storyGroupList.capacity)")
    }
    
    func storylyLoadFailed(_ storylyView: Storyly.StorylyView,
                           errorMessage: String) {
        print("StorylyFailed ===>\(errorMessage)")
    }
    
    func storylyActionClicked(_ storylyView: Storyly.StorylyView,
                                rootViewController: UIViewController,
                                story: Storyly.Story) {
        // story.media.actionUrl is important field
        print("[storyly] IntegrationViewController:storylyActionClicked - story action_url {\(story.media.actionUrl ?? "")}")
                
        guard let url = URL(string: story.media.actionUrl! ) else {
            return
                }
        UIApplication.shared.openURL(url)
    }
}










/*
 let vc = storyboard?.instantiateViewController(withIdentifier: "second_vc") as! SecondViewController
 present(vc, animated:true)
 */
