//
//  AppDelegate.swift
//  InstaShare
//
//  Created by Dogus Yigit Ozcelik on 31.05.2021.
//

import UIKit
import BrazeKit
import BrazeUI

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    static var braze: Braze? = nil


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let configuration = Braze.Configuration(
            apiKey: "03c6f5df-b373-4f79-b4dd-0f7f8e34b0f9",
            endpoint: "sdk.iad-03.braze.com"
        )
        let braze = Braze(configuration: configuration)
        AppDelegate.braze = braze
        
        // - InAppMessage UI
        let inAppMessageUI = BrazeInAppMessageUI()
        inAppMessageUI.delegate = self
        braze.inAppMessagePresenter = inAppMessageUI
        
        // Override point for customization after application launch.
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


}

extension AppDelegate: BrazeInAppMessageUIDelegate {

  func inAppMessage(
    _ ui: BrazeInAppMessageUI,
    prepareWith context: inout BrazeInAppMessageUI.PresentationContext
  ) {
      
      context.attributes?.slideup?.imageSize = CGSize(width: 65, height: 65)
      context.attributes?.slideup?.cornerRadius = 20
    // Customize the in-app message presentation here using the context
  }

  func inAppMessage(
    _ ui: BrazeInAppMessageUI,
    shouldProcess clickAction: Braze.InAppMessage.ClickAction,
    buttonId: String?,
    message: Braze.InAppMessage,
    view: InAppMessageView
  ) -> Bool {
    // Intercept the in-app message click action here
    return true
  }

  func inAppMessage(
    _ ui: BrazeInAppMessageUI,
    didPresent message: Braze.InAppMessage,
    view: InAppMessageView
  ) {
    // Executed when `message` is presented to the user
  }
    
}



