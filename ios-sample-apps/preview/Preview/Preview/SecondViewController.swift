//
//  SecondViewController.swift
//  Preview
//
//  Created by Mehmet Şahin Ayçiçek on 12.09.2022.
//

import UIKit

class SecondViewController: UIViewController {
    
    @IBAction func close () {
        let userModal = UserModal(user_id: userID.text, country_count: countryCount.text, mile_count: miles.text)
        if let presenter = presentingViewController as? ViewController {
                presenter.userModal = userModal
            }
            dismiss(animated: true, completion: nil)
        
    }
    
    @IBAction func cancel () {
        dismiss(animated: true, completion: nil)
    }

    @IBOutlet weak var userID: UITextField!
    @IBOutlet weak var countryCount: UITextField!
    @IBOutlet weak var miles: UITextField!
    
    @IBOutlet weak var saveButton: UIButton!
    
    var isFilled: Bool = false {
        didSet{
            saveButton.isEnabled = isFilled
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        userID.delegate = self
        countryCount.delegate = self
        miles.delegate = self
        // Do any additional setup after loading the view.
    }
  

}

extension SecondViewController: UITextFieldDelegate {
    func textFieldDidEndEditing(_ textField: UITextField) {
        guard let userID = userID.text,
              let countryCount = countryCount.text,
              let miles = miles.text
        else { return }
        isFilled = !userID.isEmpty || countryCount.isEmpty || miles.isEmpty
    }
}

