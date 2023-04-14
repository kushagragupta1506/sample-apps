//
//  BottomSheet.swift
//  StorylyDemo
//
//  Created by Macbook on 13.07.2022.
//  Copyright © 2022 App Samurai Inc. All rights reserved.
//

import Foundation
import UIKit


class RedBottomSheet : ViewController {
    
    var onDismiss: (() -> Void)?
    
    private lazy var nextButton: UIButton = {
        let _nextButton = UIButton()
        _nextButton.translatesAutoresizingMaskIntoConstraints = false
        return _nextButton
    }()
    
    private lazy var closeButton: UIButton = {
        let _closeButton = UIButton()
        _closeButton.translatesAutoresizingMaskIntoConstraints = false
        return _closeButton
    }()
     
    override func viewDidLoad() {
        self.view.backgroundColor = UIColor.white
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        self.onDismiss?()
    }
    
    @objc fileprivate func onNextClick(_ sender: UIButton) {
        let  bottomsheet = WhiteBottomSheet()
        if #available(iOS 15.0, *) {
            if let sheet = bottomsheet.sheetPresentationController {
                sheet.detents = [.medium()]
            }
        }
        topMostController()?.present(bottomsheet, animated: true,completion: nil)
     }
    
    @objc fileprivate func onCloseClick(_ sender: UIButton) {
        self.dismiss(animated: true)
     }
}
