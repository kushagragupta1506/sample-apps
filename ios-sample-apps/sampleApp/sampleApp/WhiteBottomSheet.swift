//
//  BottomSheet.swift
//  StorylyDemo
//
//  Created by Macbook on 13.07.2022.
//  Copyright © 2022 App Samurai Inc. All rights reserved.
//

import Foundation
import UIKit


class WhiteBottomSheet : ViewController {
    var onDismiss: (() -> Void)?
    private lazy var closeButton: UIButton = {
        let _closeButton = UIButton()
        _closeButton.translatesAutoresizingMaskIntoConstraints = false
        return _closeButton
    }()
     
    override func viewDidLoad() {
        self.view.backgroundColor = UIColor.white
        
        self.view.translatesAutoresizingMaskIntoConstraints = false
        
        self.view.addSubview(closeButton)
        
        self.closeButton.backgroundColor = UIColor.black
        self.closeButton.setTitleColor(UIColor.white, for: .normal)
        self.closeButton.setTitle("Close", for: .normal)
        self.closeButton.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 30).isActive = true
        self.closeButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -30).isActive = true
        self.closeButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor,constant: -50).isActive = true
        self.closeButton.addTarget(self, action: #selector(onCloseClick(_:)), for: .touchUpInside)
    }
    
    @objc fileprivate func onCloseClick(_ sender: UIButton) {
        self.dismiss(animated: true)
     }
}
