//
//  SecondViewController.swift
//  myStoryly
//
//  Created by Mehmet Şahin Ayçiçek on 26.12.2022.
//

import UIKit
import Storyly

class SecondViewController: UIViewController {
    @IBOutlet weak var storylyView: StorylyView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.storylyView.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTE1NDQsImluc19pZCI6MTIzNDF9.Ffue1gq3hBpLuiQ6FVseby94Lh0Y6PNLOK6suaHbnvo")
        self.storylyView.rootViewController = self
        self.storylyView.delegate = self
        // Do any additional setup after loading the view.
    }
    
    @IBAction func openStoryButton() {
        self.storylyView.openStory(storyGroupId: "49237", play: .Default)
    }

}

extension SecondViewController : StorylyDelegate {
    
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
        
        //self.storylyView.pause()
    }
}
